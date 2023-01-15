#include <iostream>
#include <thread>
#include <cmath>
#include <mutex>

//Ejercicio 1 - Práctica 12
//Autor: Manuel Pérez Ruiz

using namespace std;

void calcMontecarlo(int nPuntos);

int totalHits = 0;

int intentos = 0;

mutex mtx;

int main(){
    long nPuntos;
    cout << "Introduce el número de puntos: ";
    cin >> nPuntos;
    cout << endl;

    int nThreads;
    do{
        cout << "Introduce el número de hilos: ";
        cin >> nThreads;
        cout << endl;  
    }while(nThreads < 1);

    thread hilos[nThreads];

    for (int i = 0; i < nThreads; i++) {
        hilos[i] = thread(calcMontecarlo(nPuntos));
    }
    for (int i = 0; i < nThreads; i++) {
        hilos[i].join();
    }

    cout << "El tiempo es de: " << endl;
    cout << "El resultado final es de: " << (double)totalHits/intentos << endl;


    return 0;


}

void calcMontecarlo(int nPuntos){
    double x, y;
    int puntosDentro = 0;
    int hits = 0;

    for (int i = 0; i < nPuntos; i++) {
        x = rand();
        y = rand();
        if (y <= sqrt(1 - pow(x, 2))) {
            puntosDentro++;
        }
        hits++;
    }

    mtx.lock(); 
    totalHits += puntosDentro;
    intentos += hits;
    mtx.unlock();

}


