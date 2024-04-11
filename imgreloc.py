from mpi4py import MPI
import numpy as np
import time

comm = MPI.COMM_WORLD
rank = comm.Get_rank()
size = comm.Get_size()

def relocate_rotate(image_part):
    new_image_part = image_part * 1.1  # Relocation
    new_image_part = np.rot90(new_image_part, k=1)  # Rotation
    return new_image_part

if rank == 0:
    start_time = time.time()
    n = 1000  # Replace with your image size
    image = np.random.randint(0, 256, (n, n))

    image_parts = np.array_split(image, size)
    new_image = np.zeros_like(image)

    for i in range(1, size):
        comm.send(image_parts[i], dest=i)

    new_image_parts = [relocate_rotate(image_parts[0])]
    for i in range(1, size):
        new_image_parts.append(comm.recv(source=i))

    for i in range(size):
        new_image[i * (n // size):(i + 1) * (n // size)] = new_image_parts[i]

    end_time = time.time()
    print("Master: Image updated")
    print(f"Time taken: {end_time - start_time} seconds")

    # Print new_image or save it to a file if needed
else:
    image_part = comm.recv(source=0)
    new_image_part = relocate_rotate(image_part)
    comm.send(new_image_part, dest=0)
