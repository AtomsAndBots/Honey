package atomsandbots.android.honey.user.Model;

import java.util.List;

// Model Class for Exp. Adapter

public class ExpModel {
    private String Tittle;
    private List<ProductModel> productModels;

    public ExpModel() {
    }

    public ExpModel(String tittle, List<ProductModel> productModels) {
        Tittle = tittle;
        this.productModels = productModels;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public List<ProductModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(List<ProductModel> productModels) {
        this.productModels = productModels;
    }
}
