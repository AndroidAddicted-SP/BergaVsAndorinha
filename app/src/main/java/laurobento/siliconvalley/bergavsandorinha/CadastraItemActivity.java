package laurobento.siliconvalley.bergavsandorinha;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import laurobento.siliconvalley.bergavsandorinha.model.ItemMercado;
import laurobento.siliconvalley.bergavsandorinha.model.Mercado;

import static android.Manifest.permission.READ_CONTACTS;

public class CadastraItemActivity extends AppCompatActivity  {

    private Realm realm;

    private EditText mNomeItem;
    private EditText mDescricaoItem;
    private EditText mPrecoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_item);

        realm = Realm.getDefaultInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNomeItem = (EditText) findViewById(R.id.nomeItem);
        mDescricaoItem = (EditText) findViewById(R.id.descricaoItem);
        mPrecoItem = (EditText) findViewById(R.id.precoItem);

        Button mEmailSignInButton = (Button) findViewById(R.id.cadastrar);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Mercado mercado = realm.where(Mercado.class).equalTo("nome", "Andorinha", Case.INSENSITIVE).findFirst();
                if(mercado == null){
                    realm.beginTransaction();
                    mercado = new Mercado();
                    mercado.setNome("Andorinha");
                    mercado.setDescricao("O barato de cada dia");

                    ItemMercado item = new ItemMercado();
                    item.setNome(mNomeItem.getText().toString());
                    item.setDescricao(mDescricaoItem.getText().toString());
                    item.setPreco(mPrecoItem.getText().toString());

                    RealmList<ItemMercado> items = new RealmList<ItemMercado>();
                    items.add(item);

                    mercado.setItems(items);

                    realm.copyToRealm(mercado);

                    realm.commitTransaction();
                }else{
                    realm.beginTransaction();

                    ItemMercado item = new ItemMercado();
                    item.setNome(mNomeItem.getText().toString());
                    item.setDescricao(mDescricaoItem.getText().toString());
                    item.setPreco(mPrecoItem.getText().toString());

                    mercado.getItems().add(item);

                    realm.commitTransaction();
                }

                Log.e("ENTIDADE", "Mercado" + mercado.toString());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

}

