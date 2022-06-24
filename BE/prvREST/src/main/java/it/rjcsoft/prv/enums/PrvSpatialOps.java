package it.rjcsoft.prv.enums;

import com.querydsl.core.types.Operator;

public enum PrvSpatialOps implements Operator {

    ST_EQUALS(Boolean.class),
    ST_DISJOINT(Boolean.class),
    ST_INTERSECTS(Boolean.class),
    ST_TOUCHES(Boolean.class),
    ST_CROSSES(Boolean.class),
    ST_WITHIN(Boolean.class),
    ST_CONTAINS(Boolean.class),
    ST_OVERLAPS(Boolean.class),
    ST_RELATE(Boolean.class),
	ST_COVEREDBY(Boolean.class),
	ST_COVERS(Boolean.class);
	        
    private final Class<?> type;
	
    private PrvSpatialOps(Class<?> type) {
        this.type = type;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

}
