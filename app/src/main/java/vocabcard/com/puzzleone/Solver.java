package vocabcard.com.puzzleone;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by shivamawasthi on 16/10/15.
 */
public class Solver {
    State state;
    Node node;
    int next,c=0;
    LinkedList queue ;
    ManhattanDistance md;
    Check ck ;
    LinkedList lk;

    public Solver(State init){
        this.state = init;
        md = new ManhattanDistance();
        node = new Node();
        node.setParent(null);
        node.setState(init);
        queue = new LinkedList();
        queue.add(null);
        insert(node);
        ck=new Check(init.getState());
        if(!ck.checkState())
        {
            checker();
        }else{
        }
    }

    public void next(){
        next=1;
        node = ((Node)queue.get(next));
        queue.remove(next);
    }


    public void child(){
        ArrayList<Node> arrayList = new ArrayList<Node>();
        boolean up = false,down = false,right = false,left = false;
        int a[][]=((node).getState()).getState();
        int row=0, column=0;
        for(int i =0;i<3;i++){
            for(int j =0;j<3;j++){
                if(a[i][j]==0){
                    row = i;
                    column = j;
                }
            }
        }

        if(row==0){
            up = false;
            down = true;
        }else{
            if(row==1){
                up = true;
                down = true;
            }else{
                if(row==2){
                    up = true;
                    down = false;
                }
            }
        }
        if(column==0){
            right = true;
            left = false;
        }else{
            if(column == 1){
                right = true;
                left = true;
            }else{
                if(column == 2){
                    right = false;
                    left = true;
                }
            }
        }

        if(up){
            int temp[][] = new int[3][3];
            for(int i = 0;i<3;i++){
                for(int j=0;j<3;j++){
                    temp[i][j] = a[i][j];
                }
            }
            temp[row][column]=a[row-1][column];
            temp[row-1][column]=0;
            State s = new State();
            s.setState(temp);
            md.manhattanDistances(temp);
            s.setManhattanDistance(md.getDis());
            s.setEvaluationFunction((node.getState()).getCost()+md.getDis()+1);
            s.setCost((node.getState()).getCost()+1);
            Node n = new Node();
            n.setParent(node);
            n.setState(s);
            n.setChildren(null);
            try{
                if(!ck.checkStateDuplicate(temp, (node.getParent()).getState().getState())){
                    arrayList.add(n);
                    insert(n);
                }
            }catch(NullPointerException e){
                arrayList.add(n);
                insert(n);
            }

        }
        if(down){
            int temp[][] = new int[3][3];
            for(int i = 0;i<3;i++){
                for(int j=0;j<3;j++){
                    temp[i][j] = a[i][j];
                }
            }
            temp[row][column]=a[row+1][column];
            temp[row+1][column]=0;
            State s = new State();
            s.setState(temp);
            md.manhattanDistances(temp);
            s.setManhattanDistance(md.getDis());
            s.setEvaluationFunction((node.getState()).getCost()+md.getDis()+1);
            s.setCost((node.getState()).getCost()+1);
            Node n = new Node();
            n.setParent(node);
            n.setState(s);
            n.setChildren(null);
            try{
                if(!ck.checkStateDuplicate(temp, (node.getParent()).getState().getState())){
                    arrayList.add(n);
                    insert(n);
                }
            }catch(NullPointerException e){
                arrayList.add(n);
                insert(n);
            }

        }
        if(right){
            int temp[][] = new int[3][3];
            for(int i = 0;i<3;i++){
                for(int j=0;j<3;j++){
                    temp[i][j] = a[i][j];
                }
            }
            temp[row][column]=a[row][column+1];
            temp[row][column+1]=0;
            State s = new State();
            s.setState(temp);
            md.manhattanDistances(temp);
            s.setManhattanDistance(md.getDis());
            s.setEvaluationFunction((node.getState()).getCost()+md.getDis()+1);
            s.setCost((node.getState()).getCost()+1);
            Node n = new Node();
            n.setParent(node);
            n.setState(s);
            n.setChildren(null);
            try{
                if(!ck.checkStateDuplicate(temp, (node.getParent()).getState().getState())){
                    arrayList.add(n);
                    insert(n);
                }
            }catch(NullPointerException e){
                arrayList.add(n);
                insert(n);
            }

        }
        if(left){
            int temp[][] = new int[3][3];
            for(int i = 0;i<3;i++){
                for(int j=0;j<3;j++){
                    temp[i][j] = a[i][j];
                }
            }
            temp[row][column]=a[row][column-1];
            temp[row][column-1]=0;
            State s = new State();
            s.setState(temp);
            md.manhattanDistances(temp);
            s.setManhattanDistance(md.getDis());
            s.setEvaluationFunction((node.getState()).getCost()+md.getDis()+1);
            s.setCost((node.getState()).getCost()+1);
            Node n = new Node();
            n.setParent(node);
            n.setState(s);
            n.setChildren(null);
            try{
                if(!ck.checkStateDuplicate(temp, (node.getParent()).getState().getState())){
                    arrayList.add(n);
                    insert(n);
                }
            }catch(NullPointerException e){
                arrayList.add(n);
                insert(n);
            }

        }
        node.setChildren(arrayList);

    }

    public void checker(){
        while(!queue.isEmpty()){
            next();
            if((new Check(node.getState().getState())).checkState()){
                MainActivity.cost = node.getState().getCost();
                parent();
                break;
            }
            c++;
            child();

        }

    }


    public void parent(){
        lk = new LinkedList();
        do{
            lk.add(node.getState().getState());
        }while((node = node.getParent())!=null);


    }

    public LinkedList getMoves(){
        return lk;
    }

    public void insert(Node item){
        queue.add(item);
        int i,j,n;
        n = queue.size()-1;
        j = n;
        i = (int) Math.floor(n/2);
        while(i>0 && (((Node)queue.get(i)).getState().getEvaluationFunction())> item.getState().getEvaluationFunction()){
            queue.set(j, queue.get(i));
            j=i;
            i=(int) Math.floor(i/2);
        }
        queue.set(j, item);
    }
}
