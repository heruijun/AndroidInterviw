package interview.heruijun.com.provider.model.api;

/**
 * Created by heruijun on 2018/3/1.
 */

public class RspModel<T> {

    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
