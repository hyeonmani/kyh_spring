package hello.advenced.trace.logTrace;

import hello.advenced.trace.TraceId;
import hello.advenced.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
