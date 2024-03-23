import java.util.*;

class table{
    public int[] arr;              // referencja do wektora
    public int maxSize;             // maksymalna długosc wektora
    public int currentSize;                // aktualna długosc wektora

    //============================================= KONSTRUKTOR
    public table (int m)  {
        maxSize = m;
        currentSize = 0;
        arr = new int[maxSize];
    }
    //============================================= wczytywanie wektora
    public void read_vector(int m, Scanner scanner)  {
        if(m > 0 && m <= maxSize) {
            currentSize = m;
            for(int i=0; i<m; i++)
                arr[i]= scanner.nextInt();
        }
    }
    //============================================= losowanie wektora
    public void rand_vector(int m, int mini, int maxi){
        // losowanie m liczb z przedziału  [mini,maxi]  do tablicy arr
        // Math.random() - returns a random number between 0.0 and 1.0.

        if( (m > 0 && m <= maxSize) && (mini < maxi) ) {
            System.out.println("Losowanie " + m + " liczb z przedzialu [" + mini + ", " + maxi + "]");
            currentSize = m;

            for(int i = 0; i < currentSize; i++)
                arr[i]= (int)(Math.random() * (maxi-mini) + mini) ;
        }
        else System.out.println("Niepoprawne argumenty!!!");
    }
    //==================wypisanie tablicy====================//
    public void display()  {

        System.out.println("Dlugosc tablicy = " + currentSize);
        System.out.println("Zawartosc tablicy: ");

        for(int i = 0; i < currentSize; i++){
            System.out.print(arr[i] + ", ");

            if( (i+1)%20 == 0)      //co 20 elementow przejscie do nowej linii
                System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public void pop_back(int x) {
        arr[currentSize] = x;
        currentSize++;
    }

    public void first_appearance(int x) {
        boolean flag = false;

        for(int i=0; i<currentSize; i++){
            if(arr[i] == x){
                System.out.print("pozycja: " + i);
                flag = true;    //zaznaczam ze zadany element wystepuje w tablicy
                break;
            }
        }
        if(flag == false){
            System.out.println("zadany element nie wystepuje w tablicy");
        }
    }

    public void remove_first_appearance(int x){
        boolean flag = false;

        for(int i=0; i<currentSize; i++){
            if(flag == false && arr[i] == x){   //warunek flag == false chroni nas przed kolejnymi wykonaniami instrukcji
                flag = true;                    // w przypadku gdy w tablicy jest kilka elemtow rownych x
                currentSize--; //skoro usunelismy jeden element to aktualny rozmiar jest mniejszy o 1
            }

            if(flag == true){
                arr[i] = arr[i+1];  // skoro flag=true to wiemy ze element x zostal napotkany i od tego momentu
            }                       //wszystkie kolejne elementy tablicy przesuwamy w lewo
        }                           // pierwsze takie przesuniecie nadpisze element x ktory chcemy usunac

        if(flag == false){
            System.out.println("zadany element nie wystepuje w tablicy");
        }
    }

    public void remove_on_p(int p){
        if( p >= currentSize || p < 0) {
            System.out.println("niepoprawny indeks");
        }
        else{
            currentSize--;  //zmniejszamy rozmiar o 1 bo bedziemy usuwac jeden element

            for(int i=p; i<currentSize; i++){   //elementy tablicy o indeksie wiekszym od p przesuwamy o 1 w lewo
                arr[i] = arr[i+1];              //pierwsze takie przesuniecie nadpisze element ktory chcemy usunac
            }
        }
    }

    public void put_on_p(int x, int p){
        if( p >= currentSize || p < 0) {
            System.out.println("niepoprawny indeks");
        }
        else{
            arr[p] = x;
        }
    }

    public void remove_duplicateOn3(){

        for(int i=0; i<currentSize; i++){

            for(int j=0; j<currentSize; j++){

                if(arr[i] == arr[j] && i != j){
                    currentSize--;  //zmniejszamy rozmiar o 1 bo bedziemy usuwac jeden element

                    for(int k=j; k<currentSize; k++){   //elementy tablicy o indeksie wiekszym od p przesuwamy o 1 w lewo
                        arr[k] = arr[k+1];              //pierwsze takie przesuniecie nadpisze element ktory chcemy usunac
                    }
                    j--;
                }
            }
        }
    }

    public void remove_duplicateOn2(){
        //sortowanie algorytmem bubblesort o zlozonosci O(n2)
        for(int i=0; i<currentSize; i++){

            for(int j=i+1; j<currentSize; j++){

                if(arr[i] > arr[j]){
                    int temp = arr[i];   //=====//
                    arr[i] = arr[j];     //swap//
                    arr[j] = temp;       //====//
                }
            }
        }
        int k = 0;  //indeks pomocniczy do przepisywania tablicy
        int rozmiar = currentSize;  //zmienna pomocnicza do przechowywania rozmiaru

        for(int i=0; i<currentSize; i++){

            if(arr[i] == arr[i+1] && (i+1) != currentSize){
                rozmiar--;  //dekrementacja pomocniczego rozmiaru
                continue;   //skok do kolejnego kroku petli
            }

            arr[k] = arr[i];    //skoro jestesmy w tym miejscu to znaczy ze warunek w ifie nie zaszedl czyli przepisujemy tablice
            k++;                //inkrementacja nowego indeksu
        }
        currentSize = rozmiar;  // aktualizuje rozmiar tablicy
    }
    double result;
    public void polynomial_value(int n, int x, Scanner scanner){

        double [] coefficient = new double[n+1];
        int k=0;
        for(int i=n; i>=0; i--){    //wypelnienie wielomianu

            System.out.print("podaj wspolczynnik o indeksie " + i + ": ");
            coefficient[k] = scanner.nextDouble();
            k++;

        }
        System.out.println();
        int m = n; //zmienna pomocnicza do liczenia potegi w linii 186

        System.out.print("wielomian: ");
        for(int i=0; i<k; i++){    //wypisanie wielomianu

            if(coefficient[i] != 0){
                System.out.print((int)coefficient[i] + "x^" + n);
                if(n!=0) System.out.print(" + ");
            }n--;
        }
        System.out.println();


        for(int i=0; i<k; i++){
            result += ( coefficient[i] * Math.pow(x,m) );
            m--;
        }


        System.out.println("wartosc wielomianu dla " + x + ": " + result);
    }

    public void longest_substring(){

        //podciagi bedziemy zapisywac jako wiersze macierzy
        int [][] substrings = new int [currentSize][currentSize+1];
        // drugi rozmiar to currentSize+1 dlatego ze w tym miejscu bedziemy pamietac dlugosci podciagow

        int rows = 0;
        int columns = 0;

        for(int i=0; i<currentSize-1; i++){

            while(arr[i] <= arr[i+1]){  //warunek ciagu niemalejacego

                substrings[rows][columns] = arr[i];
                columns++;
                i++;

                //wykrycie ostatniego elementu podciagu
                if(arr[i] >= arr[i-1] && arr[i] > arr[i+1]){ //warunek ktory bedzie spelnial tylko ostatni element podciagu
                    substrings[rows][columns] = arr[i];     //ten element tez dopisujemy do wiersza
                    columns++;
                }

            }substrings[rows][currentSize] = columns; //zapisanie informacji o dlugosci podciagu w miejscu ktore nigdy
                                                      //by nie zostalo zapelnione w szczegolnym przypadku

            //przechodzimy do kolejnego wiersza macierzy czyli rows++ i zerujemy kolumny
            rows++;
            columns=0;
        }

        //zauwazmy teraz ze najdluzszy podciag znajduje sie w tym wierszu ktorego element substrings[rows][currentSize]
        // jest najwiekszy


        //znajdujemy ta wartosc
        int maks = 0;
        for(int i=0; i<rows; i++){
            if(substrings[i][currentSize] > maks){
                maks = substrings[i][currentSize];
            }
        }

        //wypisujemy te podciagi ktore sa dlugosci maks ( moze byc ich kilka )
        System.out.println("najdluzszy podciag: ");

        for(int i=0; i<rows; i++){
            if(substrings[i][currentSize] == maks){

                for(int j=0; j<substrings[i][currentSize]; j++){
                    System.out.print(substrings[i][j]+ " ");
                }
                System.out.println();
            }
        }
    }

    public void smallest_odd_indexed_element(){
        int mini = arr[1];

        for(int i=1; i<currentSize; i = i + 2){

            if(arr[i] <= mini){
                mini = arr[i];
            }
        }

        System.out.println("wartosc najm. el. o indeksie nieparzystym: " + mini);
    }

    public void index_of_largest_even_valued_element(){
        int indexOfMaks = 0;

        for(int i=0; i<currentSize; i++){

            if(arr[i] % 2 == 0){    //parzystosc
                if(arr[i] > arr[indexOfMaks]){
                    indexOfMaks = i;
                }
            }
        }

        System.out.println("indeks najw. el. o wartosci parzystej: " + indexOfMaks);
    }



}//koniec klasy table

//////////////////////////////////////////////////////////

public class Main{

    public static void main(String args[])  {

        table tab = new table(100);

        Scanner scanner = new Scanner(System.in);  	//tworzymy obiekt scanner

        System.out.print("Podaj dlugosc wektora " + "(<=" + tab.maxSize + "): ");

        int m = scanner.nextInt();
        while(m < 0 || m > tab.maxSize) {
            System.out.println("Niepoprawna dlugosc wektora");
            System.out.print("Podaj poprawna dlugosc wektora: ");
            m = scanner.nextInt();
        }

        System.out.print ("Wybierz: 1-czytanie, 2-losowanie: ");

        int p = scanner.nextInt();
        if(p == 1){
            System.out.println("Czytanie " + m + " liczb typu int");
            tab.read_vector(m, scanner);
        }
        else{
            System.out.println("Losowanie " + m + " liczb integer");
            int min=0, max=0;
            System.out.println("Podaj zakres liczb [min,max]");
            System.out.print("min: "); min = scanner.nextInt();
            System.out.print("max: "); max = scanner.nextInt();
            tab.rand_vector(m, min, max);
        }

        //wyswietlenie tablicy
        tab.display();

        int op=0;

        do {
            menu();
            System.out.print("Wybor: ");
            op = scanner.nextInt();

            switch (op) {
                case 1:
                {
                    int x = 0;
                    System.out.print("podaj element ktory chcesz dodac na koniec wektora: ");
                    x = scanner.nextInt();
                    tab.pop_back(x);

                    System.out.println("po operacji " + op);
                    tab.display();

                    break;
                }
                case 2:
                {
                    int x = 0;
                    System.out.print("podaj element ktorego pierwsza pozycje chcesz znalezc: ");
                    x = scanner.nextInt();
                    tab.first_appearance(x);

                    break;
                }
                case 3:
                {
                    int x = 0;
                    System.out.print("podaj element ktorego pierwsze wystapienie chcesz usunac: ");
                    x = scanner.nextInt();
                    tab.remove_first_appearance(x);

                    System.out.println("po operacji " + op);
                    tab.display();

                    break;
                }
                case 4:
                {
                    int x = 0;
                    System.out.print("podaj pozycje elementu ktory chcesz usunac: ");
                    x = scanner.nextInt();
                    tab.remove_on_p(x);

                    System.out.println("po operacji " + op);
                    tab.display();

                    break;
                }
                case 5:
                {
                    int x = 0;
                    int poz = 0;
                    System.out.print("podaj wartosc ktora chcesz wstawic: ");
                    x = scanner.nextInt();
                    System.out.print("podaj pozycje: ");
                    poz = scanner.nextInt();
                    tab.put_on_p(x, poz);

                    System.out.println("po operacji " + op);
                    tab.display();

                    break;
                }
                case 6:
                {
                    System.out.println("usuwam duplikaty ze zlozonoscia O(n^3)");
                    tab.remove_duplicateOn3();

                    System.out.println("po operacji " + op);
                    tab.display();

                    break;
                }
                case 7:
                {
                    System.out.println("usuwam duplikaty ze zlozonoscia O(n^2)");
                    tab.remove_duplicateOn2();

                    System.out.println("po operacji " + op);
                    tab.display();

                    break;
                }
                case 8:
                {
                    int x = 0;
                    int  n = 0;
                    System.out.print("podaj wartosc x: ");
                    x = scanner.nextInt();

                    System.out.print("podaj stopien n: ");
                    n = scanner.nextInt();

                    tab.polynomial_value(n,x,scanner);

                    break;
                }
                case 9:
                {
                    tab.longest_substring();
                    break;
                }
                case 10:
                {
                    tab.smallest_odd_indexed_element();
                    tab.index_of_largest_even_valued_element();

                    break;
                }

                default:
                    System.out.println("nie ma takiej opcji!!!");

            }   //koniec switch
        }while(op != 0);


    }

    //========================================================
    public static void menu() {
        System.out.println("\n______________________________");
        System.out.println("           Operacje ");
        System.out.println("_______________________________");
        System.out.println(" 1. Dodawanie elementu na koniec wektora");
        System.out.println(" 2. Znajdowanie pierwszej pozycji zadanego elementu");
        System.out.println(" 3. Usuwanie z tablicy pierwszego wystapienia zadanego elementu");
        System.out.println(" 4. Usuwanie z tablicy elementu z pozycji p");
        System.out.println(" 5. Wstawianie zadanej wartosci x do tablicy na zadana pozycje p");
        System.out.println(" 6. Usuwanie duplikatow z tablicy ze zlozonoscia O(n^3)");
        System.out.println(" 7. Usuwanie duplikatow z tablicy ze zlozonoscia O(n^2)");
        System.out.println(" 8. Obliczanie wartosci wielomianu y(x) stopnia n dla zadanej wartosci x");
        System.out.println(" 9. Znajdowanie najdluzszego podwektora o elementach niemalejacych");
        System.out.println(" 10. Obliczanie dwoch liczb calkowitych:\n" +
                " x - wartosc najmniejszego elementu o indeksie nieparzystym\n" +
                " y - indeks najwiekszego elementu o wartosci parzystej");
        System.out.println(" 0. Koniec programu ");
        System.out.println("===============================");
    }
    //========================================================

}


