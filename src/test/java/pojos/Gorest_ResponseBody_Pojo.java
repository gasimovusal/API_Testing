package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Gorest_ResponseBody_Pojo {

    private Object meta;
    private Gorest_Data_Pojo data;

    public Gorest_ResponseBody_Pojo(String meta, Gorest_Data_Pojo data) {
        this.meta = meta;
        this.data = data;
    }

    public Gorest_ResponseBody_Pojo() {
    }

    public Object getMeta() {
        return meta;
    }
    public void setMeta(Object meta) {
        this.meta = meta;
    }
    public Gorest_Data_Pojo getData() {
        return data;
    }
    public void setData(Gorest_Data_Pojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Gorest_ResponseBody_Pojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
