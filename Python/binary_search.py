

def binary_search(arr, target):
    lo, hi = 0, len(arr) - 1
    while lo <= hi:
        mid = (lo + hi) // 2
        if arr[mid] == target:
            return mid
        if arr[mid] < target:
            lo = mid + 1
        else:
            hi = mid - 1
    return -1

if __name__ == "__main__":
    sample = [1,2,3,4,5,7,10]
    t = 7
    idx = binary_search(sample, t)
    print(f"Index of {t} in {sample} -> {idx}")
