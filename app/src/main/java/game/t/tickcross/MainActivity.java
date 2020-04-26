package game.t.tickcross;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int count=0;
    int activePlaye=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};



    public void playerTap(final View view) throws InterruptedException {
        ImageView img=(ImageView)view;
        TextView tvv=findViewById(R.id.tv);
        int tappedImage = Integer.parseInt(img.getTag().toString());
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);

        if(gameState[tappedImage]==2){
            count++;
            img.setTranslationY(-1000f);
            if(activePlaye==0){
                gameState[tappedImage]=1;
                img.setImageResource(R.drawable.o);
                activePlaye=1;
                tvv.setText("Second player Chance");
            }
            else{
                gameState[tappedImage]=0;
                img.setImageResource(R.drawable.c);
                activePlaye=0;
                tvv.setText("First player Chance");
            }
            img.animate().translationYBy(1000f).setDuration(300);
            //tvv.setText(pname);
            if(count>8){
                tvv.setText("Exceeded");
                builder.setTitle("Exceede");
                builder.setMessage("No one wins");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        reset(view);
                    }
                });
                builder.show();
            }
            else if((gameState[0]!=2 && (gameState[0]==gameState[1])&&(gameState[1]==gameState[2])) ||
                    (gameState[3]!=2 &&(gameState[3]==gameState[4])&&(gameState[4]==gameState[5])) ||
                    (gameState[6]!=2 &&(gameState[6]==gameState[7])&&(gameState[7]==gameState[8]))||
                    (gameState[0]!=2 &&(gameState[0]==gameState[3])&&(gameState[3]==gameState[6])) ||
                    (gameState[1]!=2 &&(gameState[1]==gameState[4])&&(gameState[4]==gameState[7])) ||
                    (gameState[2]!=2 &&(gameState[2]==gameState[5])&&(gameState[5]==gameState[8])) ||
                    (gameState[0]!=2 &&(gameState[0]==gameState[4])&&(gameState[4]==gameState[8])) ||
                    (gameState[6]!=2 &&(gameState[6]==gameState[4])&&(gameState[4]==gameState[2]))){

                if(tvv.getText()=="Second player Chance" ){
                    tvv.setText("First Player won");
                    builder.setTitle("Won");
                    builder.setMessage("First Player won");
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            reset(view);
                        }
                    });
                    builder.show();
                }
                else if(tvv.getText()=="First player Chance"){
                    tvv.setText("Second Player won");
                    builder.setTitle("Won");
                    builder.setMessage("Second player won");
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            reset(view);
                        }
                    });

                    builder.show();
                }
            }

        }
    }

    public void reset(View view){
        activePlaye=0;
        TextView tvv=findViewById(R.id.tv);
        count=0;
        tvv.setText("First player Chance");
        for(int i=0;i<gameState.length;i++)
            gameState[i]=2;

        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView11)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView12)).setImageResource(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


/*
        final ImageView i1=findViewById(R.id.imageView4);
        final ImageView i2=findViewById(R.id.imageView5);
        final ImageView i3=findViewById(R.id.imageView6);
        final ImageView i4=findViewById(R.id.imageView7);
        final ImageView i5=findViewById(R.id.imageView8);
        final ImageView i6=findViewById(R.id.imageView9);
        final ImageView i7=findViewById(R.id.imageView10);
        final ImageView i8=findViewById(R.id.imageView11);
        final ImageView i9=findViewById(R.id.imageView12);
        final Button back=findViewById(R.id.button2);
        final Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        //ImageView i1=findViewById(R.id.imageView4);

        final byte check[]={0,0,0,0,0,0,0,0,0};



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(tictactoe.this,MainActivity.class);
                startActivity(intent);

                if(Build.VERSION.SDK_INT>=26){
                    vibrator.vibrate(VibrationEffect.createOneShot(100,VibrationEffect.DEFAULT_AMPLITUDE));
                }
                else{
                    vibrator.vibrate(100);
                }
            }
        });


        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i1.getDrawable()==null){
                    //Toast.makeText(tictactoe.this, "aya na bhau", Toast.LENGTH_SHORT).show();
                    if(count%2==0) {
                        i1.setImageResource(R.drawable.c);
                        check[0]=1;
                    }
                    else{
                        i1.setImageResource(R.drawable.o);
                        check[0]=-1;
                    }
                    count++;
                }

            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i2.getDrawable()==null){
                    if(count%2==0) {
                        i2.setImageResource(R.drawable.c);
                        check[1]=1;
                    }
                    else{
                        i2.setImageResource(R.drawable.o);
                        check[1]=-1;
                    }
                    count++;
                }
            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i3.getDrawable()==null){
                    if(count%2==0) {
                        i3.setImageResource(R.drawable.c);
                        check[2]=1;
                    }
                    else{
                        i3.setImageResource(R.drawable.o);
                        check[2]=-1;
                    }
                    count++;
                }
            }
        });

        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i4.getDrawable()==null){
                    if(count%2==0) {
                        i4.setImageResource(R.drawable.c);
                        check[3]=1;
                    }
                    else{
                        i4.setImageResource(R.drawable.o);
                        check[3]=-1;
                    }
                    count++;
                }
            }
        });

        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i5.getDrawable()==null){
                    if(count%2==0) {
                        i5.setImageResource(R.drawable.c);
                        check[4]=1;
                    }
                    else{
                        i5.setImageResource(R.drawable.o);
                        check[4]=-1;
                    }
                    count++;
                }
            }
        });

        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i6.getDrawable()==null){
                    if(count%2==0) {
                        i6.setImageResource(R.drawable.c);
                        check[5]=1;
                    }
                    else{
                        i6.setImageResource(R.drawable.o);
                        check[5]=-1;
                    }
                    count++;
                }
            }
        });

        i7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i7.getDrawable()==null){
                    if(count%2==0) {
                        i7.setImageResource(R.drawable.c);
                        check[6]=1;
                    }
                    else{
                        i7.setImageResource(R.drawable.o);
                        check[6]=-1;
                    }
                    count++;
                }
            }
        });

        i8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i8.getDrawable()==null){
                    if(count%2==0) {
                        i8.setImageResource(R.drawable.c);
                        check[7]=1;
                    }
                    else{
                        i8.setImageResource(R.drawable.o);
                        check[7]=-1;
                    }
                    count++;
                }
            }
        });

        i9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i9.getDrawable()==null){
                    if(count%2==0) {
                        i9.setImageResource(R.drawable.c);
                        check[8]=1;
                    }
                    else{
                        i9.setImageResource(R.drawable.o);
                        check[8]=-1;
                    }
                    count++;

                }

            }
        });
        AlertDialog.Builder builder=new AlertDialog.Builder(tictactoe.this);

    }

 */
    }
}
