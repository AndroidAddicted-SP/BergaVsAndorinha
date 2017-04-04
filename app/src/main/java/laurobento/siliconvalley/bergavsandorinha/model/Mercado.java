package laurobento.siliconvalley.bergavsandorinha.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by lcunha on 02/04/17.
 */

public class Mercado extends RealmObject{
    private String nome;
    private String descricao;
    private RealmList<ItemMercado> items;

    public Mercado() {}

    public Mercado(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public RealmList<ItemMercado> getItems() {
        return items;
    }

    public void setItems(RealmList<ItemMercado> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Mercado{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", items=" + items.toString() +
                '}';
    }
}
