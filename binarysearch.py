# Python program to search an element in row-wise
# and column-wise sorted matrix

def binarySearch(mat, target):
    
    n = len(mat)
    low, high = 0, n - 1

    # Standard binary search algorithm
    while low <= high:
        mid = (low + high) // 2  # Midpoint index

        if mat[mid] == target:
            return True  # Element found
        elif target > mat[mid]:
            low = mid + 1  # Search in the right half
        else:
            high = mid - 1  # Search in the left half

    return False  # Element not found

def matSearch(mat, x):

    n = len(mat)
    m = len(mat[0])

    # Iterate over each row and perform binary search
    for i in range(n):
        if binarySearch(mat[i], x):
            return True  # Element found in one of the rows

    return False  # Element not found in any row
    
if __name__ == "__main__":
    mat = [
        [3, 30, 38],
        [20, 52, 54],
        [35, 60, 69]
    ]
    x = 35
    if matSearch(mat, x):
        print("true")
    else:
        print("false")
