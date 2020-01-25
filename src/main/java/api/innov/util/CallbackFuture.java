package api.innov.util;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import okhttp3.*;

/**
 * @author Michalis Pefkianakis
 *
 */

public class CallbackFuture extends CompletableFuture<Response> implements Callback {

	@Override
	public void onResponse(Call call, Response response) {		
		super.complete(response);
	}
 
	
	@Override
	public void onFailure(Call call, IOException e) {		
		super.completeExceptionally(e);
	}

	
	public void onFailure(Request arg0, IOException arg1) {		
		arg1.printStackTrace();
	}

	
	public void onResponse(Response response) throws IOException { 
		super.complete(response);
	}

}