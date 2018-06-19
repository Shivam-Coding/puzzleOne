package vocabcard.com.puzzleone;

/**
 * Created by shivamawasthi on 16/10/15.
 */
public class ManhattanDistance {

    private int dis=0;

    public int getDis() {
        return dis;
    }

    public void manhattanDistances(int play[][]){
        dis=0;
        int fin[][]={{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
        for(int j=0;j<3;j++){
            for(int z=0;z<3;z++){
                switch(play[j][z]){
                    case 0:{

                        break;
                    }
                    case 1:{
                        dis = dis+(Math.abs(fin[1][0]-j))+(Math.abs(fin[1][1]-z));

                        break;
                    }
                    case 2:{
                        dis = dis+(Math.abs(fin[2][0]-j))+(Math.abs(fin[2][1]-z));

                        break;
                    }
                    case 3:{
                        dis = dis+(Math.abs(fin[3][0]-j))+(Math.abs(fin[3][1]-z));

                        break;
                    }
                    case 4:{
                        dis = dis+(Math.abs(fin[4][0]-j))+(Math.abs(fin[4][1]-z));

                        break;
                    }
                    case 5:{
                        dis = dis+(Math.abs(fin[5][0]-j))+(Math.abs(fin[5][1]-z));

                        break;
                    }
                    case 6:{
                        dis = dis+(Math.abs(fin[6][0]-j))+(Math.abs(fin[6][1]-z));

                        break;
                    }
                    case 7:{
                        dis = dis+(Math.abs(fin[7][0]-j))+(Math.abs(fin[7][1]-z));

                        break;
                    }
                    case 8:{
                        dis = dis+(Math.abs(fin[8][0]-j))+(Math.abs(fin[8][1]-z));

                        break;
                    }

                }
            }
        }
    }

}

