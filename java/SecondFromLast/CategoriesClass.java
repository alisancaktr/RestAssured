package SecondFromLast;

import java.util.List;

public class CategoriesClass {

    private String id;

    private String name;

    private List<String> translateName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTranslateName() {
        return translateName;
    }

    public void setTranslateName(List<String> translateName) {
        this.translateName = translateName;
    }

    @Override
    public String toString() {
        return "CategoriesClass{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", translateName=" + translateName +
                '}';
    }

}
