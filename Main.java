public class Main {
    public static void main(String[] args) {
        System.out.println("10 Pseudorandom Integers");
        int[] random = random(10, 10);
        print(random);
        mergeSort(random);
        System.out.println("Sorted with Merge Sort");
        print(random);
        int target = (int) (Math.random() * 10);
        System.out.println("Target: " + target);
        int index = recursiveBinarySearch(random, 0, random.length - 1, target);
        System.out.println("Index: " + index);
        if (index != -1) {
            System.out.println("Element: " + random[index]);
            System.out.println(target == random[index]);
        }
        System.out.println("Target found: " + iterativeSearch(random, target));
        System.out.println(("Target found: " + iterativeBinarySearch(random, target)));
    }

    public static int[] random(int length, int max) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (Math.random() * max);
        return arr;
    }

    public static void print(int[] arr) {
        for (int i : arr) System.out.print(i + " ");
        System.out.println();
    }

    public static void mergeSort(int[] elements) {
        int n = elements.length;
        int[] temp = new int[n];
        mergeSortHelper(elements, 0, n - 1, temp);
    }

    private static void mergeSortHelper(int[] elements, int from, int to, int[] temp) {
        if (from < to) //As long as there are elements to sort...
        {
            int middle = (from + to) / 2; //Split it in half.
            mergeSortHelper(elements, from, middle, temp); //Split the left half.
            mergeSortHelper(elements, middle + 1, to, temp); //Split the right half.
            merge(elements, from, middle, to, temp); //Merge the split arrays.
        }
    }

    private static void merge(int[] elements, int from, int mid, int to, int[] temp) {
        int i = from; //Left Index
        int j = mid + 1; //Right Index
        int k = from; //Temp Index
        while (i <= mid && j <= to) //Traverse both arrays.
        {
            if (elements[i] < elements[j]) //If the left is less than the right,
            {
                temp[k] = elements[i]; //copy the left element.
                i++; //Increment the left index.
            } else //If the right is less than the left,
            {
                temp[k] = elements[j]; //Copy the right element.
                j++; //Increment right index.
            }
            k++; //Increment temp index.
        }
        while (i <= mid) //If there are leftover elements in the left array,
        {
            temp[k] = elements[i]; //Copy them to the temp array.
            i++; //Increment left index.
            k++; //Incremenet temp index.
        }
        while (j <= to) //If there are leftover elements in the right array,
        {
            temp[k] = elements[j]; //Copy them to the temp array.
            j++; //Increment right index.
            k++; //Increment temp index.
        }
        for (k = from; k <= to; k++) //For all elements,
        {
            elements[k] = temp[k]; //Copy them to the original array.
        }
    }

    public static int recursiveBinarySearch(int[] arr, int left, int right, int target) {
        int middle = (left + right) / 2; //Split the array in half.
        if (target == arr[middle]) return middle; //You found it!
        if (right < left) return -1; //You didn't find it.
        if (target < arr[middle]) //The middle is too large.
            return recursiveBinarySearch(arr, left, middle - 1, target); //Search the left.
        if (target > arr[middle]) //The middle is too small.
            return recursiveBinarySearch(arr, middle + 1, right, target); //Search the right.
        return -1; //You didn't find it.
    }
    public static boolean iterativeBinarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static boolean iterativeSearch(int[] arr, int target) {
        for (int i : arr) {
            if (i == target) {
                return true;
            }
        }
        return false;
    }
}