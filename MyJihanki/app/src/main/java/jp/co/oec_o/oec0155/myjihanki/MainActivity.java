package jp.co.oec_o.oec0155.myjihanki;

import android.content.ClipData;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class MainActivity extends AppCompatActivity
        implements View.OnDragListener {
    /*
        @BindView(R.id.kouka500)
        ImageView kouka500;
        @BindView(R.id.kouka100)
        ImageView kouka100;
        @BindView(R.id.kouka10)
        ImageView kouka10;
        @BindView(R.id.syasin1)
        ImageView syasin1;
        @BindView(R.id.syasin2)
        ImageView syasin2;
        @BindView(R.id.syasin3)
        ImageView syasin3;
        @BindView(R.id.kakaku1)
        TextView kakaku1;
        @BindView(R.id.kakaku2)
        TextView kakaku2;
        @BindView(R.id.kakaku3)
        TextView kakaku3;
        @BindView(R.id.button1)
        Button button1;
        @BindView(R.id.button2)
        Button button2;
        @BindView(R.id.button3)
        Button button3;
        @BindView(R.id.toridasiguti)
        ImageView toridasiguti;
        @BindView(R.id.goukeihyoji)
        TextView goukeihyoji;
        @BindView(R.id.oturihyoji)
        TextView oturihyoji;
        @BindView(R.id.tonyuguti)
        ImageView tonyuguti;
    */
    @BindView(R.id.syasin1)
    ImageView syasin1;
    @BindView(R.id.kakaku1)
    TextView kakaku1;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.syasin2)
    ImageView syasin2;
    @BindView(R.id.kakaku2)
    TextView kakaku2;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.syasin3)
    ImageView syasin3;
    @BindView(R.id.kakaku3)
    TextView kakaku3;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.toridasiguti)
    ImageView toridasiguti;
    @BindView(R.id.goukeihyoji)
    TextView goukeihyoji;
    @BindView(R.id.oturihyoji)
    TextView oturihyoji;
    @BindView(R.id.tonyuguti)
    ImageView tonyuguti;
    @BindView(R.id.kouka500)
    ImageView kouka500;
    @BindView(R.id.kouka100)
    ImageView kouka100;
    @BindView(R.id.kouka10)
    ImageView kouka10;

    int tonyukingaku;
    int goukeikingaku;
    int oturikingaku;
    int kakaku1kingaku;
    int kakaku2kingaku;
    int kakaku3kingaku;
    SoundPool sp;
    int sound1;
    int sound2;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView7)
    TextView textView7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tonyuguti.setOnDragListener(this);
        syokiSyori();
    }

    /*
    *  初期処理
     */
    public void syokiSyori() {
        buttonAllOff();
        // 商品１の価格を変数に格納
        kakaku1kingaku = Integer.parseInt((String) kakaku1.getText());
        // 商品２の価格を変数に格納
        kakaku2kingaku = Integer.parseInt((String) kakaku2.getText());
        // 商品３の価格を変数に格納
        kakaku3kingaku = Integer.parseInt((String) kakaku3.getText());
        sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        sound1 = sp.load(this, R.raw.hyun1, 1);
        sound2 = sp.load(this, R.raw.touch1, 1);
    }

    // 硬貨をタッチした時の処理。　ドラッグが開始される
    @OnTouch({R.id.kouka500, R.id.kouka100, R.id.kouka10})
    public boolean touchKouka(ImageView img) {
        String text = "";
        if (img == kouka500) {
            text = "500";
        } else if (img == kouka100) {
            text = "100";
        } else if (img == kouka10) {
            text = "10";
        }
        ClipData clipData = ClipData.newPlainText("kouka", text);
        img.startDrag(clipData, new View.DragShadowBuilder(img), (Object) img, 0);
        return true;
    }

    // 取り出し口をタッチした時の処理、　商品が消えて、おつりが消える
    @OnTouch(R.id.toridasiguti)
    public boolean touchToridasi(ImageView img) {
        toridasiguti.setImageDrawable(null);
        oturihyoji.setText("");
        return true;
    }

    // 硬貨をドロップした時の処理
    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            if (v == tonyuguti) {
                ClipData clipData = event.getClipData();
                ClipData.Item item = clipData.getItemAt(0);
                tonyukingaku = Integer.parseInt((String) item.getText());
                tonyuSyori();
            }
        }
        return true;
    }

    // お金が投入された時の処理
    public void tonyuSyori() {
        goukeikingaku = goukeikingaku + tonyukingaku;
        goukeihyoji.setText(String.valueOf(goukeikingaku));
        if (goukeikingaku >= kakaku1kingaku) {
            button1On();
        }
        if (goukeikingaku >= kakaku2kingaku) {
            button2On();
        }
        if (goukeikingaku >= kakaku3kingaku) {
            button3On();
        }
        // お金を投入した時の音を鳴らす
        sp.play(sound1, 1.0f, 1.0f, 0, 0, 1.0f);
    }


    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                btn1Syori();
                break;
            case R.id.button2:
                btn2Syori();
                break;
            case R.id.button3:
                btn3Syori();
                break;
        }
    }

    /**
     * ボタン１が押された時の処理
     */
    public void btn1Syori() {
        oturikingaku = goukeikingaku - kakaku1kingaku;
        toridasiguti.setImageDrawable(syasin1.getDrawable());
        oturihyoji.setText(String.valueOf(oturikingaku));
        // 商品が出てくる音を鳴らす
        sp.play(sound2, 1.0f, 1.0f, 0, 0, 1.0f);
        buttonAllOff();
    }

    /**
     * ボタン２が押された時の処理
     */
    public void btn2Syori() {
        oturikingaku = goukeikingaku - kakaku2kingaku;
        toridasiguti.setImageDrawable(syasin2.getDrawable());
        oturihyoji.setText(String.valueOf(oturikingaku));
        // 商品が出てくる音を鳴らす
        sp.play(sound2, 1.0f, 1.0f, 0, 0, 1.0f);
        buttonAllOff();
    }

    /**
     * ボタン３が押された時の処理
     */
    public void btn3Syori() {
        oturikingaku = goukeikingaku - kakaku3kingaku;
        toridasiguti.setImageDrawable(syasin3.getDrawable());
        oturihyoji.setText(String.valueOf(oturikingaku));
        // 商品が出てくる音を鳴らす
        sp.play(sound2, 1.0f, 1.0f, 0, 0, 1.0f);
        buttonAllOff();
    }

    // ボタン１をＯＮにする処理
    private void button1On() {
        button1.setEnabled(true);
        button1.setBackgroundColor(Color.MAGENTA);
    }

    // ボタン２をＯＮにする処理
    private void button2On() {
        button2.setEnabled(true);
        button2.setBackgroundColor(Color.MAGENTA);
    }

    // ボタン３をＯＮにする処理
    private void button3On() {
        button3.setEnabled(true);
        button3.setBackgroundColor(Color.MAGENTA);
    }

    // ボタンをオールＯＦＦにする処理
    private void buttonAllOff() {
        button1.setEnabled(false);
        button1.setBackgroundColor(Color.GRAY);
        button2.setEnabled(false);
        button2.setBackgroundColor(Color.GRAY);
        button3.setEnabled(false);
        button3.setBackgroundColor(Color.GRAY);
        goukeihyoji.setText("");
        goukeikingaku = 0;
    }


}
