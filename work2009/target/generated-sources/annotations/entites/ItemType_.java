package entites;

import entites.ItemType;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-21T20:00:40")
@StaticMetamodel(ItemType.class)
public class ItemType_ { 

    public static volatile ListAttribute<ItemType, ItemType> itemTypes;
    public static volatile SingularAttribute<ItemType, Integer> price;
    public static volatile SingularAttribute<ItemType, String> name;
    public static volatile SingularAttribute<ItemType, String> description;
    public static volatile SingularAttribute<ItemType, Long> id;

}