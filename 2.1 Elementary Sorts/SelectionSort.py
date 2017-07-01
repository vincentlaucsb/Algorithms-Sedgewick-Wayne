''' Sort in place using selection_sort '''
def selection_sort(arr):
    arr_length = len(arr)

    for i in range(0, arr_length):
        min_index = i
        
        for j in range(i + 1, arr_length):
            if arr[j] < arr[min_index]:
                min_index = j
                
        # Perform swap
        temp = arr[i]
        arr[i] = arr[min_index]
        arr[min_index] = temp