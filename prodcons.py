from mpi4py import MPI
import time

comm = MPI.COMM_WORLD
rank = comm.Get_rank()

def factorial_partial(start, end):
    result = 1
    for i in range(start, end + 1):
        result *= i
    return result

if rank == 0:
    start_time = time.time()
    n = 10  # Replace with your value of n
    total_factorial = 1
    for i in range(1, n + 1):
        total_factorial *= i
    end_time = time.time()
    print(f"Master: Total factorial = {total_factorial}")
    print(f"Time taken: {end_time - start_time} seconds")
else:
    partial = factorial_partial(1, rank)
    comm.send(partial, dest=0)
