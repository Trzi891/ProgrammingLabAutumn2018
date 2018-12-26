 class HelpOpen {
    HelpOpen() {
        try {
            String url = "https://www.wikihow.com/Play-Mahjong";
            java.net.URI uri = java.net.URI.create(url);
            java.awt.Desktop dp = java.awt.Desktop.getDesktop();
            if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                dp.browse(uri);
            }
        } catch (java.lang.NullPointerException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}
