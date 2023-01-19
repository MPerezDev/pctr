#include <iostream>
#include <thread>
#include <cmath>
#include <mutex>

//Ejercicio 1 - Práctica 12
//Autor: Manuel Pérez Ruiz

using namespace std;

void calcMontecarlo(int nPuntos);
//Hacemos uso de una variable global para almacenar el número de puntos que caen dentro del área
int totalHits = 0;

//Hacemos uso de una variable global para almacenar el número de intentos
int intentos = 0;

//Hacemos uso de un mutex para controlar el acceso a las variables compartidas
mutex mtx;

//Función principal
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

    chrono::time_point<chrono::system_clock> start, end;
    start = chrono::system_clock::now();

    thread hilos[nThreads];

    for (int i = 0; i < nThreads; i++) {
        hilos[i] = thread(calcMontecarlo,nPuntos/nThreads);
    }
    for (int i = 0; i < nThreads; i++) {
        hilos[i].join();
    }

    end = chrono::system_clock::now();
    chrono::duration<double> elapsed_seconds = end-start;

    cout << "El tiempo es de: " << elapsed_seconds.count() << " segundos" << endl;
    cout << "El resultado final es de: " << (double)totalHits/intentos << endl;


    return 0;


}

//Función que calcula el número de puntos que caen dentro del área
void calcMontecarlo(int nPuntos){
    double x, y;
    int puntosDentro = 0;
    int puntos = 0;

    for (int i = 0; i < nPuntos; i++) {
        x = (double) rand() / RAND_MAX;
        y = (double) rand() / RAND_MAX;
        if (y <= pow(x, 2)) {
            puntosDentro++;
        }
        puntos++;
    }

    mtx.lock(); 
    totalHits += puntosDentro;
    intentos += puntos;
    mtx.unlock();

}


