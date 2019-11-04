package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

    int move = 1;
    RecyclerView recyclerView;
    MyAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    TextView resultView;
    String[] a = new String[9];
    final int[] logos = new int[3];
    boolean[] done= new boolean[9];
    boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        resultView = findViewById(R.id.result);
        recyclerView = findViewById(R.id.grid);
        recyclerView.setHasFixedSize(true);

        Arrays.fill(logos,R.drawable.mixcloud);
        Arrays.fill(a," ");
        Arrays.fill(done,false);

        final Button restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arrays.fill(logos,R.drawable.mixcloud);
                Arrays.fill(a," ");
                Arrays.fill(done,false);
                adapter.notifyDataSetChanged();
                restart.setVisibility(View.GONE);
                resultView.setText(null);
                move = 1;
                recyclerView.setClickable(true);
            }
        });

        adapter = new MyAdapter(a,this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(!gameOver && !done[position]) {
                    if(move == 1) {
                        a[position] = "X";
                        move = 2;
                    }
                    else {
                        a[position] = "O";
                        move = 1;
                    }
                    done[position] = true;
                    adapter.notifyItemChanged(position);
                }

                gameOver = checkWin(a);
                if(gameOver) {
                    restart.setVisibility(View.VISIBLE);
                    recyclerView.setClickable(false);
                }
            }
        });

        layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
    }

    public boolean checkWin(String[] b)
    {
        int[][] a = new int[b.length][b.length];

        for(int i=0;i<b.length;i++) {
            switch(b[i]) {
                case " " : a[i/3][i%3] = 0; break;
                case "X" : a[i/3][i%3] = 1; break;
                case "O" : a[i/3][i%3] = 2; break;
            }
        }

        for(int i=0;i<3;i++)
            if(a[0][i] == a[1][i] && a[0][i] == a[2][i]) {
                if(a[0][i] == 1)
                    resultView.setText(PlayerInfo.name1 + " WINS!!!");

                else if(a[0][i] == 2)
                    resultView.setText(PlayerInfo.name2 + " WINS!!!");
                if(a[0][i] != 0) return true;
            }

        for(int i=0;i<3;i++)
            if(a[i][0] == a[i][1] && a[i][0] == a[i][2]) {
                if(a[i][0] == 1)
                    resultView.setText(PlayerInfo.name1 + " WINS!!!");
                else if(a[i][0] == 2)
                    resultView.setText(PlayerInfo.name2 + " WINS!!!");
                if(a[i][0] != 0) return true;
            }

        if(a[0][0] == a[1][1] && a[0][0] == a[2][2]) {
            if(a[0][0] == 1)
                resultView.setText(PlayerInfo.name1 + " WINS!!!");
            else if(a[0][0] == 2)
                resultView.setText(PlayerInfo.name2 + " WINS!!!");
            if(a[0][0] != 0) return true;
        }

        if(a[0][2] == a[1][1] && a[0][2] == a[2][0]) {
            if(a[0][2] == 1)
                resultView.setText(PlayerInfo.name1 + " WINS!!!");
            else if(a[0][2] == 2)
                resultView.setText(PlayerInfo.name2 + " WINS!!!");
            if(a[0][2] != 0) return true;
        }

        int c = 0;
        for(int i=0;i<9;i++) if(done[i]) c++;
        if(c==9) {
            resultView.setText("TIE GAME!!");
            return true;
        }
        return false;
    }
}
