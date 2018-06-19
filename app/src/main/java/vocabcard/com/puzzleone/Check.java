package vocabcard.com.puzzleone;

/**
 * Created by shivamawasthi on 16/10/15.
 */
public class Check {

    int finalState[][]={{0,1,2},{3,4,5},{6,7,8}};
    int[][] currentState;

    public Check(int init[][]){
        this.currentState=init;
    }
    public Check(){

    }

    public boolean checkState(){
        for(int i =0;i<3;i++){
            for(int j =0;j<3;j++){
                if(finalState[i][j]!=currentState[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkStateDuplicate(int a[][],int b[][]){
        try{
            for(int i =0;i<3;i++){
                for(int j =0;j<3;j++){
                    if(a[i][j]!=b[i][j]){
                        return false;
                    }
                }
            }
        }catch(NullPointerException n){
            System.out.println("null pointer ");
        }
        return true;
    }

}
