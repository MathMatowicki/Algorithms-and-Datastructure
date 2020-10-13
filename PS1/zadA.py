counter = 0 

def NewtonIteration(n, k):
    out = 1
    counterIteration = 0
    for i in range(1, k + 1):
        counterIteration += 1
        out = out * (n - i + 1) / i

    print("Counter of Iteration Alghorithm: ", counterIteration)
    return out

def NewtonRecursion(n, k):
    global counter
    if k == 0 or k == n:
        return 1
    else:
        counter += 1
        return NewtonRecursion(n-1, k-1)+NewtonRecursion(n-1, k)
    return counter

print(counter)
print(NewtonIteration(10, 2))
print(NewtonRecursion(10, 2))
