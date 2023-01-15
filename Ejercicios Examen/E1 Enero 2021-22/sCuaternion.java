import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class sCuaternion extends UnicastRemoteObject implements ICuaternion{


    public sCuaternion() throws RemoteException{
         
    }

    public float[] sumCuaternion(float[] q1,float[] q2) throws RemoteException{
        float [] q = new float[4];
        q[0] = q1[0] + q2[0];
        q[1] = q1[1] + q2[1];
        q[2] = q1[2] + q2[2];
        q[3] = q1[3] + q2[3];
        return q;
        
    }

    public float[] conCuaternion(float[] q) throws RemoteException{
        float [] qCon = new float[4];
        qCon[0] = q[0];
        qCon[1] = -q[1];
        qCon[2] = -q[2];
        qCon[3] = -q[3];
        return qCon;
        
    }

    public float[] xCuaternion(float[] q, float p) throws RemoteException{
        float [] qX = new float[4];
        qX[0] = q[0] * p;
        qX[1] = q[1] * p;
        qX[2] = q[2] * p;
        qX[3] = q[3] * p;
        return qX;

    }

    public float tCuaternion(float[] q) throws RemoteException{

        return q[0] + q[1] + q[2] + q[3];

    }

    public static void main(String[] args) {
        
        try{
            sCuaternion s = new sCuaternion();
            sCuaternion s2 = new sCuaternion();
            sCuaternion s3 = new sCuaternion();
            sCuaternion s4 = new sCuaternion();

            Naming.bind("suma", s);
            Naming.bind("conjugado", s2);
            Naming.bind("escalado", s3);
            Naming.bind("traza", s4);
            
            System.out.println("Servidor preparado");


        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}