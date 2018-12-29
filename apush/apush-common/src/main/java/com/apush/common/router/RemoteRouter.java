package com.apush.common.router;

import com.apush.api.router.ClientLocation;
import com.apush.api.router.Router;

/**
 * 远程路由器
 * @author madongyu-ds
 *
 */
public final class RemoteRouter implements Router<ClientLocation> {
    private final ClientLocation clientLocation;

    public RemoteRouter(ClientLocation clientLocation) {
        this.clientLocation = clientLocation;
    }

    public boolean isOnline(){
        return clientLocation.isOnline();
    }

    public boolean isOffline(){
        return clientLocation.isOffline();
    }

    @Override
    public ClientLocation getRouteValue() {
        return clientLocation;
    }

    @Override
    public RouterType getRouteType() {
        return RouterType.REMOTE;
    }

    @Override
    public String toString() {
        return "RemoteRouter{" + clientLocation + '}';
    }
}
