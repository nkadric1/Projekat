package ba.unsa.etf.rpr.domain;
/** @author Nerma Kadric
 *
* forcing POJO beans to have ID field

 */

public interface Idable {
    void setId(int id);
    int getId();
}
