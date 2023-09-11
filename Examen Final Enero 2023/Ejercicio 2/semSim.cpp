#include <iostream>
#include <mutex>
#include <condition_variable>
#include <vector>
#include <thread>
#include "math.h"


using namespace std;


struct semSim{
  
  int n = 0;
  
  mutex cerrojo;
  condition_variable nEmpty;
  condition_variable nFull;
  
  semSim(){}
  
  void decSemaforo() {

    unique_lock<mutex> uniqueLock(cerrojo);

    n--;

    nEmpty.notify_one();
    uniqueLock.unlock();

  }	

  void incSemaforo() {

    unique_lock<mutex> uniqueLock(cerrojo);

    n++;

    nFull.notify_one();
    uniqueLock.unlock();

  }  

  int main() {

    cout << "main creando semaforo y hebras concurrentes..." << endl;
    //Declaramos un vector de thread
    vector<thread> hilos;

    //Declaramos un semaforo
    semSim semaforo;

    //Declaramos una variable auxiliar para el numero de hilos
    int aux = 2;

    for(int i=0; i<aux; i++ ) {
      hilos.push_back(thread([&semaforo](){for(int i=0;i<pow(4,10);i++){semaforo.incSemaforo();}}));
      hilos.push_back(thread([&semaforo](){for(int i=0;i<pow(4,10);i++){semaforo.decSemaforo();}}));
    }

    cout << "hebras procesando..." << endl;

    for(int i=0; i < hilos.size(); i++ ){
      hilos[i].join();

    }

    cout << "main mostrando valor del contador compartido: " << semaforo.n;

    return 0;

  }
  	  
};