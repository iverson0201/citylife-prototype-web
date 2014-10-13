package com.citylife.backend.common;
import java.util.Comparator;
import com.citylife.backend.domain.Base;

public class DateDescCom implements Comparator<Base> {
	@Override
	public int compare(Base o1, Base o2) {
		if(o1.getCreatedAt().before(o2.getCreatedAt())){
			return 1;
		}else if(o1.getCreatedAt().after(o2.getCreatedAt())){
			return -1;
		}else{
			return o1.getId().compareTo(o2.getId());
		}
	}
}
