#include <iostream>
using namespace std;

int partion(int *arr, int s, int e)
{
    // Take pivot
    int p = arr[s];
    int cnt = 0;

    // find right posn of pivot
    for (int i = s + 1; i <= e; i++)
    {
        if (arr[i] <= p)
            cnt++;
    }

    // place pivot at right position
    int pIndex = s + cnt;
    swap(arr[pIndex], arr[s]);

    // Now, partion kro
    int i = s;
    int j = e;

    while (i < pIndex && j > pIndex)
    {
        while (arr[i] < p) // left part
        {
            i++;
        }

        while (arr[j] > p)
        {
            j--;
        }

        if (i < pIndex && j > pIndex)
        {
            swap(arr[i++], arr[j--]);
        }
    }
    return pIndex;
}

void quickSort(int *arr, int s, int e)
{
    // base case
    if (s >= e)
        return;

    // partition
    int pivot = partion(arr, s, e);

    // left part sort
    quickSort(arr, s, pivot - 1);

    // right part sort
    quickSort(arr, pivot + 1, e);
}

int main()
{
    int arr[] = {3, 1, 4, 5, 2};
    int n = sizeof(arr) / sizeof(int);

    quickSort(arr, 0, n - 1);

    cout << "Quick Sorting" << endl;
    for (int i = 0; i < n; i++)
    {
        cout << arr[i] << " ";
    }

    return 0;
}
