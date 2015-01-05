package onclick.com.raisesurvey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.onclick.model.Offer;

import java.util.List;

/**
 * Created by Ronald T on 12/20/2014.
 */
public class OfferListViewAdapter extends ArrayAdapter {

    protected List<Offer> offerList;
    public OfferListViewAdapter(Context context, int resource) {
        super(context, resource);

    }

    public OfferListViewAdapter(Context context, int resource, List<Offer> offers) {
        super(context, resource, offers);
        this.offerList = offers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.offer_list_item, null);
        }

        Offer offerListItem = offerList.get(position);

        if (offerListItem != null) {

            TextView tBrand = (TextView) v.findViewById(R.id.listItemBrand);
            TextView tValue = (TextView) v.findViewById(R.id.listItemValue);
            TextView tCost = (TextView) v.findViewById(R.id.listItemCost);

            if (tBrand != null) {
                tBrand.setText(offerListItem.getCardBrand());
            }
            if (tValue != null) {

                tValue.setText((String.valueOf(offerListItem.getCardValue())));
            }
            if (tCost != null) {

                tCost.setText((String.valueOf(offerListItem.getCardCost())));
            }
        }

        return v;
    }
}
