package ar.edu.unlam.pb2;

import java.util.Comparator;

public class PorCbuComparator implements Comparator<Cuenta>{

	@Override
	public int compare(Cuenta o1, Cuenta o2) {
		return o1.getCBU().compareTo(o2.getCBU());
	}

}
