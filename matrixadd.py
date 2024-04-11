from mpi4py import MPI
import numpy as np
import time

comm = MPI.COMM_WORLD
rank = comm.Get_rank()
size = comm.Get_size()

def matrix_sum(matrix_part):
    return np.sum(matrix_part)

if rank == 0:
    start_time = time.time()
    n = 1000  # Replace with your matrix size
    matrix = np.random.randint(1, 10, (n, n))
    matrix_parts = np.array_split(matrix, size)
    partial_sums = []

    for i in range(1, size):
        comm.send(matrix_parts[i], dest=i)
    
    partial_sum = matrix_sum(matrix_parts[0])
    for i in range(1, size):
        partial_sum += comm.recv(source=i)
    
    end_time = time.time()
    print(f"Master: Total sum = {partial_sum}")
    print(f"Time taken: {end_time - start_time} seconds")
else:
    matrix_part = comm.recv(source=0)
    partial_sum = matrix_sum(matrix_part)
    comm.send(partial_sum, dest=0)
