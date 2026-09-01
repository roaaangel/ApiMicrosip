/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.cobrosxdepositar;

import com.app.models.AbonoMaestroEntity;
import com.app.models.SerieFolioCXC;
import java.util.List;

/**
 *
 * @author burtebony
 */
public class CobroXDepositar {
    private List<AbonoMaestroEntity> listaAbonosParaMicrosip;
    
    private SerieFolioCXC serieFolioCXC;

    /**
     * @return the listaAbonosParaMicrosip
     */
    public List<AbonoMaestroEntity> getListaAbonosParaMicrosip() {
        return listaAbonosParaMicrosip;
    }

    /**
     * @param listaAbonosParaMicrosip the listaAbonosParaMicrosip to set
     */
    public void setListaAbonosParaMicrosip(List<AbonoMaestroEntity> listaAbonosParaMicrosip) {
        this.listaAbonosParaMicrosip = listaAbonosParaMicrosip;
    }

    /**
     * @return the serieFolioCXC
     */
    public SerieFolioCXC getSerieFolioCXC() {
        return serieFolioCXC;
    }

    /**
     * @param serieFolioCXC the serieFolioCXC to set
     */
    public void setSerieFolioCXC(SerieFolioCXC serieFolioCXC) {
        this.serieFolioCXC = serieFolioCXC;
    }
}
