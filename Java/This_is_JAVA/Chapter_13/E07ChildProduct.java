package Chapter_13;

public class E07ChildProduct<T, M, C> extends E07Product<T, M> {
	private C company;

	public C getCompany() {
		return company;
	}

	public void setCompany(C company) {
		this.company = company;
	}
}
