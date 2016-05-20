package jp.co.oec_o.oec0155.myjihanki;

import android.content.ClipData;
import android.graphics.Color;
import android.media.MediaPlayer;
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

    // 投入金額を入れる変数
    int tonyukingaku;
    // 合計金額を入れる変数
    int goukeikingaku;
    // おつりを入れる変数
    int oturikingaku;
    // 商品１の価格を入れる変数
    int kakaku1kingaku;
    // 商品２の価格を入れる変数
    int kakaku2kingaku;
    // 商品３の価格をいれる変数
    int kakaku3kingaku;
    // お金を入れた時の音
    MediaPlayer sound1;
    // 商品が出る時の音
    MediaPlayer sound2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tonyuguti.setOnDragListener(this);
        syokiSyori();
    }

    // プログラムがスタートした時に最初に行う処理
    public void syokiSyori() {
        //　ボタンを全てオフにして押せなくする
        buttonAllOff();
        // 商品１、２，３の価格を変数に格納
        kakaku1kingaku = Integer.parseInt((String) kakaku1.getText());
        kakaku2kingaku = Integer.parseInt((String) kakaku2.getText());
        kakaku3kingaku = Integer.parseInt((String) kakaku3.getText());
        // 音を出す準備
        sound1 = MediaPlayer.create(this, R.raw.hyun1);
        sound2 = MediaPlayer.create(this, R.raw.touch1);
    }

    // ボタンを全てオフにする
    private void buttonAllOff() {
        button1.setEnabled(false);
        button1.setBackgroundColor(Color.GRAY);
        button2.setEnabled(false);
        button2.setBackgroundColor(Color.GRAY);
        button3.setEnabled(false);
        button3.setBackgroundColor(Color.GRAY);
    }

    // 500円硬貨をタッチした時の処理。　ドラッグが開始される
    @OnTouch(R.id.kouka500)
    public boolean touchKouka500(ImageView img) {
        ClipData clipData = ClipData.newPlainText("kouka", "500");
        img.startDrag(clipData, new View.DragShadowBuilder(img), (Object) img, 0);
        return true;
    }
    // 100円硬貨をタッチした時の処理。　ドラッグが開始される
    @OnTouch( R.id.kouka100)
    public boolean touchKouka100(ImageView img) {
        ClipData clipData = ClipData.newPlainText("kouka", "100");
        img.startDrag(clipData, new View.DragShadowBuilder(img), (Object) img, 0);
        return true;
    }
    // 10円硬貨をタッチした時の処理。　ドラッグが開始される
    @OnTouch( R.id.kouka10)
    public boolean touchKouka10(ImageView img) {
        ClipData clipData = ClipData.newPlainText("kouka", "10");
        img.startDrag(clipData, new View.DragShadowBuilder(img), (Object) img, 0);
        return true;
    }

    // 硬貨をドロップした時の処理
    @Override
    public boolean onDrag(View v, DragEvent event) {
        // ドロップした時
        if (event.getAction() == DragEvent.ACTION_DROP) {
            // ドロップした先が投入口だったら
            if (v == tonyuguti) {
                // クリップデータを取り出して、投入金額変数に数字に変換してセットする
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
        sound1.start();
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



    // ボタン１が押された時の処理
    @OnClick(R.id.button1)
    public void onClick1() {
        oturikingaku = goukeikingaku - kakaku1kingaku;
        toridasiguti.setImageDrawable(syasin1.getDrawable());
        oturihyoji.setText(String.valueOf(oturikingaku));
        buttonAllOff();
        // 商品が出てくる音を鳴らす
        sound2.start();
    }

     // ボタン２が押された時の処理
    @OnClick(R.id.button2)
    public void onClick2() {
        oturikingaku = goukeikingaku - kakaku2kingaku;
        toridasiguti.setImageDrawable(syasin2.getDrawable());
        oturihyoji.setText(String.valueOf(oturikingaku));
        buttonAllOff();
        // 商品が出てくる音を鳴らす
        sound2.start();
    }

     // ボタン３が押された時の処理
    @OnClick(R.id.button3)
    public void onClick3() {
        oturikingaku = goukeikingaku - kakaku3kingaku;
        toridasiguti.setImageDrawable(syasin3.getDrawable());
        oturihyoji.setText(String.valueOf(oturikingaku));
        buttonAllOff();
        // 商品が出てくる音を鳴らす
        sound2.start();
    }

    // 取り出し口をタッチした時の処理、　商品が消えて、おつりが消える
    @OnClick(R.id.toridasiguti)
    public void onClick() {
        toridasiguti.setImageDrawable(null);
        goukeikingaku = 0;
        goukeihyoji.setText("");
        oturikingaku = 0;
        oturihyoji.setText("");
    }


}
