from mpi4py import MPI
import numpy as np
import time

comm = MPI.COMM_WORLD
rank = comm.Get_rank()
size = comm.Get_size()

def bucket_sort(numbers):
    max_digits = 3
    for d in range(max_digits):
        buckets = [[] for _ in range(10)]

        for num in numbers:
            digit = (num // 10**d) % 10
            buckets[digit].append(num)
        
        numbers.clear()
        for bucket in buckets:
            numbers.extend(bucket)
        
        numbers.sort()

    return numbers

if rank == 0:
    start_time = time.time()
    n = 100000  # Replace with your value of n
    numbers = list(np.random.randint(0, 1000, n))

    chunk_size = n // size
    for i in range(size):
        start = i * chunk_size
        end = start + chunk_size if i != size - 1 else n
        comm.send(numbers[start:end], dest=i)

    sorted_parts = []
    for i in range(size):
        sorted_parts.extend(comm.recv(source=i))
    
    sorted_numbers = bucket_sort(sorted_parts)

    end_time = time.time()
    print(f"Master: Sorted numbers = {sorted_numbers}")
    print(f"Time taken: {end_time - start_time} seconds")
else:
    numbers = comm.recv(source=0)
    sorted_numbers = bucket_sort(numbers)
    comm.send(sorted_numbers, dest=0)
