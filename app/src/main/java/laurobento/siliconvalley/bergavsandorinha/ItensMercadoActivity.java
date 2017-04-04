package laurobento.siliconvalley.bergavsandorinha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import laurobento.siliconvalley.bergavsandorinha.model.ItemMercado;
import laurobento.siliconvalley.bergavsandorinha.model.Mercado;

public class ItensMercadoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerViewAdapter listAdapter;
    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_mercado);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        List<ItemMercado> list = createList();
        listAdapter = new RecyclerViewAdapter(list);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);

    }

    private List<ItemMercado> createList() {
        List<ItemMercado> items = new ArrayList<>();
        realm = Realm.getDefaultInstance();
        Mercado mercado = realm.where(Mercado.class).equalTo("nome", "Andorinha", Case.INSENSITIVE).findFirst();

        if(mercado != null) {
            for (ItemMercado item : mercado.getItems()) {
                items.add(item);
            }
        }

        return items;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
