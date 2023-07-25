import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public class Request {
    private final String requestLine;
    private final String method;
    private final String resourcePath;
    private final String query;
    private final List<NameValuePair> queryParams;


    public Request(String requestLine) {
        this.requestLine = requestLine;

        String[] requestLineParts = requestLine.split(" ");
        this.method = requestLineParts[0];
        URI uri = URI.create(requestLineParts[1]);

        int indexSplit = requestLineParts[1].indexOf('?');
        this.resourcePath = uri.getPath();
        this.query = uri.getQuery();
        this.queryParams = URLEncodedUtils.parse(URI.create(requestLineParts[1]), "UTF-8");
    }

    public List<NameValuePair> getQueryParams() {
        return queryParams;
    }

    public List<NameValuePair> getQueryParam(String name) {

        return queryParams.stream()
                .filter(p -> p.getName().equals(name))
                .collect(Collectors.toList());
    }

    public String getRequestLine() {
        return requestLine;
    }

    public String getMethod() {
        return method;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public String getQuery() {
        return query;
    }
}
