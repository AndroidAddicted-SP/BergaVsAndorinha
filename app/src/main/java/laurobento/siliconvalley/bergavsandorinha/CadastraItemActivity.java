package laurobento.siliconvalley.bergavsandorinha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmList;
import laurobento.siliconvalley.bergavsandorinha.model.ItemMercado;
import laurobento.siliconvalley.bergavsandorinha.model.Mercado;

public class CadastraItemActivity extends AppCompatActivity  {

    private Realm realm;
    private String nomeItem;

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

        carregaDados();

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

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void carregaDados(){
        nomeItem = getIntent().getStringExtra("ITEM_NOME");
        if(nomeItem != null){
            ItemMercado item = realm.where(ItemMercado.class).equalTo("nome", nomeItem, Case.INSENSITIVE).findFirst();

            mNomeItem.setText(item.getNome());
            mDescricaoItem.setText(item.getDescricao());
            mPrecoItem.setText(item.getPreco());
        }
    }

}

