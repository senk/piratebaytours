from rest_framework import serializers
from .models import Ship, Tour, Quota, Agent, Customer, Reservation


class ShipSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Ship
        fields = ('id', 'name', 'seats')


class TourSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Tour
        fields = ('id', 'name', 'date', 'time', 'ship')


class QuotaSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Quota
        fields = ('count', 'tour', 'agent')


class AgentSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Agent
        fields = ('id', 'name')


class CustomerSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Customer
        fields = ('id', 'name')


class ReservationSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Reservation
        fields = ('id', 'count', 'tour', 'customer')
