package reactor;

import java.nio.channels.SelectionKey;

public interface IEventHandler {

    public void handleEvent(SelectionKey handle) throws Exception;

}
