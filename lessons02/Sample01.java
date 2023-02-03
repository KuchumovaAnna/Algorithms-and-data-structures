package lessons02;

import java.util.Random;

public class Sample01 {

    private static final Random random = new Random();

    public static void main(String[] args) {

        int[] arr = ArrayUtils.prepareArray();
        /*ArrayUtils.printArray(arr);
        SortUtils.directSort(arr);
        ArrayUtils.printArray(arr);
        System.out.println();
        arr = ArrayUtils.prepareArray();
        ArrayUtils.printArray(arr);
        SortUtils.quickSort(arr);
        ArrayUtils.printArray(arr);
        System.out.println();
        arr = ArrayUtils.prepareArray();*/
        ArrayUtils.printArray(arr);
        SortUtils.sort(arr);
        ArrayUtils.printArray(arr);

        System.out.println();

        //-----

        int[] testArray = ArrayUtils.prepareArray(500000);

        long startTime = System.currentTimeMillis();
        SortUtils.directSort(testArray.clone());
        long endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;
        System.out.printf("Время работы сортировки выбором: %d мс.\n", processingTime);


        //testArray = ArrayUtils.prepareArray(100000);
        startTime = System.currentTimeMillis();
        SortUtils.quickSort(testArray.clone());
        endTime = System.currentTimeMillis();
        processingTime = endTime - startTime;
        System.out.printf("Время работы быстрой сортировки: %d мс.\n", processingTime);


        startTime = System.currentTimeMillis();
        SortUtils.sort(testArray.clone());
        endTime = System.currentTimeMillis();
        processingTime = endTime - startTime;
        System.out.printf("Время работы пирамидальной сортировки: %d мс.\n", processingTime);

        System.out.println();

        /*int[] testArray2 = new int[]{  90, 22, -4, 0, 11, 2, -20, 9};
        ArrayUtils.printArray(testArray2);
        SortUtils.quickSort(testArray2);
        ArrayUtils.printArray(testArray2);
        int res2 = Arrays.binarySearch(testArray2, 22);
        System.out.printf("Элемент %d %s\n", 22,
                res2 >= 0 ? String.format("найден по индексу %d", res2) : "не найден в массиве");
        int res = SearchUtils.binarySearch(22, testArray2);
        System.out.printf("Элемент %d %s\n", 22,
                res >= 0 ? String.format("найден по индексу %d", res) : "не найден в массиве");*/

        /*
        int a = 1;
        int b = 2;
        int c;
        // if - then - else
        if ( a > b){
            c = a;
        }
        else {
            c = b;
        }
        c = a > b ? a : b;*/



    }

    static class SearchUtils{

        /**
         * Бинарный поиск в массиве
         * @param value искомое значение
         * @param array массив
         * @return результат поиска (индекс элемента массива, - 1 - элемент не найден)
         */
        public static int binarySearch(int value, int[] array) {
            return binarySearch(value, array, 0, array.length - 1);
        }

        /**
         * Алгоритм бинарного поиска
         * @param value искомое значение
         * @param array массив
         * @param min начальный индекс поиска элемента массива
         * @param max конечный индекс поиска элемента массива
         * @return результат поиска (индекс элемента массива, - 1 - элемент не найден)
         */
        public static int binarySearch(int value, int[] array, int min, int max){
            int middle; // индекс середины массива
            if (max < min)
                return -1;

            middle = (max - min) / 2 + min;

            if (array[middle] < value){
                return binarySearch(value, array, middle + 1, max);
            }
            else {
                if (array[middle] > value){
                    return binarySearch(value, array, min, middle - 1);
                }
                else {
                    return middle;
                }
            }


        }

    }

    static class SortUtils{

        public static  void sort(int[] array){
            for(int i = (array.length / 2) - 1; i >= 0; i--){
                heapify(array, array.length, i);
            }
            for (int i = array.length - 1; i >= 0; i--){
                array[0] = array[i] + array[0];
                array[i] = array[0] - array[i];
                array[0] = array[0] - array[i];
                heapify(array, i, 0);
            }
        }

        public static void heapify(int[] array, int heapSize, int rootIndex){
            int largest = rootIndex;
            int leftChild = 2 * rootIndex + 1;
            int rightChild = 2 * rootIndex + 2;
            if (leftChild < heapSize && array[leftChild] > array[largest]){
                largest = leftChild;
            }
            if (rightChild < heapSize && array[rightChild] > array[largest]){
                largest = rightChild;
            }
            if (largest != rootIndex){
                int buf = array[rootIndex];
                array[rootIndex] = array[largest];
                array[largest] = buf;

                heapify(array, heapSize, largest);
            }
        }


        /**
         * Сортировка выбором
         *O(n^2)
         *
         * 54101
         * 3 -> 0
         *
         *
         *
         * @param array
         */
        public static void directSort(int[] array){
            for (int i = 0; i < array.length; i++){
                int min = i;
                for(int j = i + 1; j < array.length; j++){
                    if (array[j] < array[min]){
                        min = j;
                    }
                }
                if (i != min){

                    array[i] = array[i] + array[min];
                    array[min] = array[i] - array[min];
                    array[i] = array[i] - array[min];
                   // int buf = array[i];
                   // array[i] = array[min];
                   // array[min] = buf;
                }
            }
        }

        /**
         * O(n^2)
         * O(n*log(n))
         * O(n)
         * @param array
         */
        static void quickSort(int[] array){
            quickSort(array, 0, array.length - 1);
        }

        static void quickSort(int[] array, int startPosition, int endPosition){
            int leftPosition = startPosition;
            int rightPosition = endPosition;

            // Вычисляем индекс и значение опорного элемента
            int pivot = array[(startPosition + endPosition) / 2];

            do{
                    while (array[leftPosition] < pivot)
                        leftPosition++;

                    while (array[rightPosition] > pivot)
                        rightPosition--;

                    if (leftPosition <= rightPosition){
                        if (leftPosition < rightPosition){
                            int buf = array[leftPosition];
                            array[leftPosition] = array[rightPosition];
                            array[rightPosition] = buf;
                        }
                        leftPosition++;
                        rightPosition--;
                    }

            }
            while (leftPosition <= rightPosition);

            if (leftPosition < endPosition){
                quickSort(array, leftPosition, endPosition);
            }
            if(startPosition < rightPosition){
                quickSort(array, startPosition, rightPosition);
            }
        }

    }

    static class ArrayUtils{

        static int[] prepareArray(){
            int[] arr = new int[random.nextInt(16) + 5];
            for (int i = 0; i < arr.length; i++){
                arr[i] = random.nextInt(100);
            }
            return arr;
        }

        static int[] prepareArray(int length){
            int[] arr = new int[length];
            for (int i = 0; i < arr.length; i++){
                arr[i] = random.nextInt(100);
            }
            return arr;
        }

        static void printArray(int[] arr){
            for (int element: arr)
                System.out.printf("%d\t", element);
            System.out.println();
        }

    }


}