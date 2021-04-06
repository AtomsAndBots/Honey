package atomsandbots.android.honey.user.Model;

import java.io.Serializable;

public class ProductModel implements Serializable {
    private String ProductName, Description, Price, Category, ProductId,Image,URL;
    private boolean Sponsored;

    public ProductModel() {
    }

    public ProductModel(String productName, String description, String price, String category, String productId, String image, String URL, boolean sponsored) {
        ProductName = productName;
        Description = description;
        Price = price;
        Category = category;
        ProductId = productId;
        Image = image;
        this.URL = URL;
        Sponsored = sponsored;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public boolean isSponsored() {
        return Sponsored;
    }

    public void setSponcered(boolean sponcered) {
        Sponsored = sponcered;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}