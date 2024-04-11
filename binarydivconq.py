from mpi4py import MPI
import time

comm = MPI.COMM_WORLD
rank = comm.Get_rank()
size = comm.Get_size()

def partial_sum(numbers):
    return sum(numbers)

if rank == 0:
    start_time = time.time()
    n = 1000000  # Replace with your value of n
    numbers = list(range(1, n + 1))
    chunk_size = n // size
    partial_sums = []

    for i in range(size):
        start = i * chunk_size
        end = start + chunk_size if i != size - 1 else n
        comm.send(numbers[start:end], dest=i)

    partial_sum = partial_sum(numbers[0:chunk_size])
    for i in range(1, size):
        partial_sum += comm.recv(source=i)
    
    end_time = time.time()
    print(f"Master: Total sum = {partial_sum}")
    print(f"Time taken: {end_time - start_time} seconds")
else:
    numbers = comm.recv(source=0)
    partial_sum = partial_sum(numbers)
    comm.send(partial_sum, dest=0)
