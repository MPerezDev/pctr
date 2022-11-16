package P5;

public class resImagen {

    private int k = 400;
    private int matriz [][] = new int [k][k];

    public resImagen() {}

    public void valorMatriz(){
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                matriz[i][j] = (int) (Math.random() * 256);
            }
        }
    }

    public void run(){
        for(int i = 0; i < k; i++){
            for(int j = 0; j < k; j++){
                if(i != 0 || j != 0 || i != k-1 || j != k-1){
                    matriz[i][j] = (4*matriz[i][j] - matriz[i-1][j] - matriz[i+1][j] - matriz[i][j-1] - matriz[i][j+1])/8;
                }

            }

        }
    }

    public static void main(String[] args) {
        resImagen res = new resImagen();
        res.valorMatriz();
        res.run();
    }

}
