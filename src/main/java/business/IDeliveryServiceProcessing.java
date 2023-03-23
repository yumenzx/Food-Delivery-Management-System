package business;

import java.util.HashMap;

/**
 * interfata pentru definirea atributelor aplicatiei principale
 */
public interface IDeliveryServiceProcessing {

    /**
     * metoda pentru importarea unui set de produse
     * @param p produsele supuse importarii
     * @require p sa nu fie null
     * @ensure importarea se va realiza cu succes
     */
    void importProducts(HashMap<String, MenuItem> p);

    /**
     * metoda de adaugare u unui produs
     * @param m produsul propriu zis
     * @require m sa nu fie null si sa nu se gaseasca inca in tabela
     * @ensure produsul este adaugat
     */
    void addProduct(MenuItem m);

    /**
     * metode de modificare a unui produs existent
     * @param identifier identificatorul produsului
     * @param m          produsul dupa care se va face actualizarea
     * @require produsul dat prin identificator si produsul dat ca parametru sa existe
     * @ensure produsul a fost modificat cu succes
     */
    void modifyProduct(String identifier, MenuItem m);

    /**
     * metoda de stergere a unui produs existent
     * @param title identificatorul produsului
     * @require produsul sa existe
     * @ensure produsul este sters cu succes
     */
    void removeProduct(String title);

    /**
     * returneaza lista de comenzi intre un interval orar dat ca parametru
     * @param startHour ora de inceput din intervalul orar
     * @param endHour   ora de sfarsit din intervalul orar
     * @return lista de comenzi intre intervalul orar dat ca si parametru
     * @require intervalul orar determinat de parametrii functiei sa fie valida 0-24
     * @ensure lista returnata in urma generarii raportului 1 sa nu fie null
     */
    Object[][] getReportByCriteria1(int startHour, int endHour);

    /**
     * returneaza produsele comandate de mai multe ori decat parametrul
     * @param orderedMoreThan valoarea comenzilor pentru produse care trebuie sa depaseasca valoarea parametrului
     * @return lista de produse comandate de mai mult decat orderedMoreThan ori
     * @require valoarea parametrului functiei sa fie pozitiva
     * @ensure lista returnata in urma generarii raportului 2 sa nu fie null
     */
    Object[][] getReportByCriteria2(int orderedMoreThan);

    /**
     * returneaza clientii care au comandat de mai multe ori decat orderedMoreThan si valoarea comenzilor a depasit valueBiggerThan
     * @param orderedMoreThan valoarea comenzilor care trebuie sa depaseasca valoarea parametrului
     * @param valueBiggerThan valoarea pretului care trebuie sa depaseasca valoarea parametrului
     * @return lista de clienti care au comandat de mai mult de orderedMoreThan ori si valoarea comenzii a depasit valueBiggerThan
     * @require valorile date ca si parametrii sa fie pozitive
     * @ensure lista returnata in urma generarii raportului 3 sa nu fie null
     */
    Object[][] getReportByCriteria3(int orderedMoreThan, int valueBiggerThan);

    /**
     * returneaza produsele comandate intr-o zi specifica cu numarul de comenzi nrTimes
     * @param specifiedDay valoarea zilei respective
     * @param nrTimes      valoarea nurului respectiv
     * @return lista de produse comandate in ziua specifiedDay de nrTimes ori
     * @require ziua specificata sa fie una valida si nrTimes sa fie pozitiva
     * @ensure lista returnata in urma generarii raportului 4 sa nu fie null
     */
    Object[][] getReportByCriteria4(int specifiedDay, int nrTimes);


    /**
     * metoda pentru a plasa o noua comanda
     * @param o comanda propriu zisa
     * @require comanda se nu fie null, adica sa existe
     * @ensure comanda a fost adaugata
     */
    void placeOrder(Order o);

    /**
     * returneaza lista de produse in urma aplicarii criteriilor de cautare
     * @param keyWord     cuvant cheie
     * @param minRating   valoarea minima a ratingului
     * @param minCalories valoarea minima a numarului de calorii
     * @param maxCalories valoarea maxima a numarului de calorii
     * @param minProtein  valoarea minima a numarului de proteine
     * @param maxProtein  valoarea maxima a numarului de proteine
     * @param minFat      valoarea minima a numarulului de fat
     * @param maxFat      valoarea maxima a numarulului de fat
     * @param minSodium   valoarea minima a numarului de sodium
     * @param maxSodium   valoarea maxima a numarului de sodium
     * @param minPrice    valoarea minima a pretului
     * @param maxPrice    valoarea maxima a pretului
     * @return lista de produse dupa aplicarea criteriului de cautare
     * @require criteriul de cautare sa fie valid, adica intervalele de cautare existe
     * @ensure lista returnata in urma criteriului de cautare sa nu fie null
     */
    Object[][] getProductsByCriteria(String keyWord, int minRating, int minCalories, int maxCalories, int minProtein, int maxProtein, int minFat, int maxFat, int minSodium, int maxSodium, int minPrice, int maxPrice);

}
