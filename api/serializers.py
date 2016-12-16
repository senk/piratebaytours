from rest_framework import serializers
from .models import Ship, Tour, Quota, Agent, Customer, Reservation


class ShipSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Ship
        fields = ('name', 'seats')


class TourSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Tour
        fields = ('name', 'date', 'time', 'ship')


class QuotaSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Quota
        fields = ('count', 'tour')


class AgentSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Agent
        fields = ('name', 'quotas')


class CustomerSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Customer
        fields = ('name')


class ReservationSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Reservation
        fields = ('count', 'tour', 'customer')
