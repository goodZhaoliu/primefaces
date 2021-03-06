/**
 * Copyright 2009-2017 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.util;

import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.primefaces.context.RequestContext;

public class AutoUpdateComponentPhaseListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {

    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
        if (!RequestContext.getCurrentInstance(context).isIgnoreAutoUpdate()) {
            return;
        }

        ArrayList<String> clientIds = AutoUpdateComponentListener.getAutoUpdateComponentClientIds(context);
        if (clientIds != null && !clientIds.isEmpty()) {
            for (int i = 0; i < clientIds.size(); i++) {
                String clientId = clientIds.get(i);
                if (!context.getPartialViewContext().getRenderIds().contains(clientId)) {
                    context.getPartialViewContext().getRenderIds().add(clientId);
                }
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

}
