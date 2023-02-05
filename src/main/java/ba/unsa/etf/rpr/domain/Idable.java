package ba.unsa.etf.rpr.domain;
/** @author Kadric Nerma
* Forcing POJO beans to have ID field
 */

public interface Idable {
    void setId(int id);
    int getId();
}
