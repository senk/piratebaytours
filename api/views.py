from rest_framework import viewsets
from django_filters.rest_framework import DjangoFilterBackend
from .models import Ship, Tour, Quota, Agent, Customer, Reservation
from .serializers import (ShipSerializer, TourSerializer, QuotaSerializer,
                          AgentSerializer, CustomerSerializer,
                          ReservationSerializer)


class ShipViewSet(viewsets.ModelViewSet):
    """
    API Endpoint that allows Ships to be viewed or edited.
    """
    queryset = Ship.objects.all().order_by('name')
    serializer_class = ShipSerializer


class TourViewSet(viewsets.ModelViewSet):
    """
    API Endpoint that allows Tours to be viewed or edited.
    """
    queryset = Tour.objects.all().order_by('name')
    serializer_class = TourSerializer


class QuotaViewSet(viewsets.ModelViewSet):
    """
    API Endpoint that allows Quotas to be viewed or edited.
    """
    queryset = Quota.objects.all()
    serializer_class = QuotaSerializer
    filter_backends = (DjangoFilterBackend,)
    filter_fields = ('agent',)


class AgentViewSet(viewsets.ModelViewSet):
    """
    API Endpoint that allows Agents to be viewed or edited.
    """
    queryset = Agent.objects.all().order_by('name')
    serializer_class = AgentSerializer


class CustomerViewSet(viewsets.ModelViewSet):
    """
    API Endpoint that allows Customers to be viewed or edited.
    """
    queryset = Customer.objects.all().order_by('name')
    serializer_class = CustomerSerializer


class ReservationViewSet(viewsets.ModelViewSet):
    """
    API Endpoint that allows Reservations to be viewed or edited.
    """
    queryset = Reservation.objects.all()
    serializer_class = ReservationSerializer
