from copy import copy
import matplotlib.patches as mpatches
import matplotlib.pylab as plt

class VisualSelectionSort:
    def __init__(self, arr):
        ''' 
        Arguments:
         * self.arr:     Array to be sorted
        '''
        
        self.arr = arr
        self.len = len(arr)
        self.trace = []
    
    def _add_trace(self, arr, min_pos, i):
        ''' Arguments:
         * arr:     Copy of the array as it is
         * min_pos: Position of minimal entry (to be swapped)
         * i:       Position of current entry
        '''

        # Convert letters to numbers
        if isinstance(arr[i], str):
            if arr[i].isalpha():
                arr = [self._alpha_to_numeric(i) for i in arr]
        
        untouched = {
            'x': [a for a, b in enumerate(arr) if a < i],
            'y': [b for a, b in enumerate(arr) if a < i]}
            
        compared = {
            'x': [a for a, b in enumerate(arr) if a != min_pos and a >= i],
            'y': [b for a, b in enumerate(arr) if a != min_pos and a >= i]}

        min_entry = {'x': [min_pos], 'y': [arr[min_pos]]}
        
        self.trace.append({
            'untouched': untouched,
            'compared': compared,
            'min': min_entry,
        })
        
    def _alpha_to_numeric(self, ltr):
        ''' Convert an alphabetical letter to a number corresponding to its position
            in in the alphabet '''
        return ord(ltr.lower()) - 96
        
    def sort(self):
        arr_length = len(self.arr)

        for i in range(0, arr_length):
            min_index = i
            
            for j in range(i + 1, arr_length):
                if self.arr[j] < self.arr[min_index]:
                    min_index = j
                    
            # Keep track of self.array changes
            self._add_trace(arr=copy(self.arr), min_pos=min_index, i=i)
                    
            # Perform swap
            temp = self.arr[i]
            self.arr[i] = self.arr[min_index]
            self.arr[min_index] = temp
            
    def plot(self):
        nrow = self.len/2
        ncol = 2
    
        def plot_one(plot_num):
            ''' Color scheme from:  https://color.adobe.com/Red-and-smooth-color-theme-19069/
            '''
            
            curr_trace = fig.add_subplot(nrow, ncol, i + 1)
            curr_trace.axis('off')
            
            untouched = curr_trace.bar(
                left=self.trace[plot_num]["untouched"]["x"],
                height=self.trace[plot_num]["untouched"]["y"],
                color="#E7E8D1")
            
            compared = curr_trace.bar(
                left=self.trace[plot_num]["compared"]["x"],
                height=self.trace[plot_num]["compared"]["y"],
                color="#424242")
            
            min = curr_trace.bar(
                left=self.trace[plot_num]["min"]["x"],
                height=self.trace[plot_num]["min"]["y"],
                color="#8E001C")
            
        # Allocate half an inch for each row, but a minimum of 5 inches total
        fig = plt.figure(figsize=(8, max(5, 0.5 * nrow)))
        fig.suptitle("Selection Sort Trace")
        
        for i in range(0, len(self.arr)):            
            plot_one(i)
            
        # Add legend
        untouched = mpatches.Patch(color='#E7E8D1')
        compared = mpatches.Patch(color='#424242')
        min = mpatches.Patch(color='#8E001C')
        
        fig.legend(handles=[untouched, compared, min],
            labels=['Untouched', 'Compared', 'Minimum'])
            
if __name__ == '__main__':
    from VisualSelectionSort import VisualSelectionSort
    from random import uniform

    import matplotlib.pylab as plt
    
    a = ['E', 'A', 'S', 'Y', 'Q', 'U', 'E', 'S', 'T', 'I', 'O', 'N']
    b = VisualSelectionSort(a)

    b.sort()
    b.plot()
    
    x = [uniform(0, 100) for i in range(0, 50)]
    y = VisualSelectionSort(x)
    y.sort()
    y.plot()
    
    plt.show()