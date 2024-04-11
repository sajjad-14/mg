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
    middle = n // 2
    partial1 = factorial_partial(1, middle)
    partial2 = comm.recv(source=1)
    total_factorial = partial1 * partial2
    end_time = time.time()
    print(f"Master: Total factorial = {total_factorial}")
    print(f"Time taken: {end_time - start_time} seconds")
else:
    if rank == 1:
        middle = n // 2
        partial = factorial_partial(middle + 1, n)
        comm.send(partial, dest=0)
