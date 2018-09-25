// "Copyright [2018] Gavan Adrian-George, 324CA"
public class Parameters {
	int idxIncConc;
	int timpRamas;
	long cost;
	int nrConcursuri;
	
	void transfer(Parameters params) {
		this.idxIncConc = params.idxIncConc;
		this.timpRamas = params.timpRamas;
		this.cost = params.cost;
		this.nrConcursuri = params.nrConcursuri;
	}
}
